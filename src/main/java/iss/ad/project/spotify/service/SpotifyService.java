package iss.ad.project.spotify.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fm.last.musicbrainz.coverart.CoverArt;
import fm.last.musicbrainz.coverart.CoverArtArchiveClient;
import fm.last.musicbrainz.coverart.CoverArtImage;
import fm.last.musicbrainz.coverart.impl.DefaultCoverArtArchiveClient;
import iss.ad.project.spotify.model.SpotifySong;
import iss.ad.project.spotify.repo.SpotifyRepo;
import lombok.Getter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class SpotifyService {
    private final SpotifyRepo spotifyRepo;

    @Getter
    private List<String> layer1IdsCache;

    @Getter
    private List<String> layer2IdsCache;
    @Getter
    private Map<String, List<String>> layer1ToLayer2MapCache;
    @Getter
    private Map<String, List<SpotifySong>> layer2ToSongsMapCache;

    private String clientId = "f0e05bb6bfe343ac92110d079daaf4b9";
    private String clientSecret = "99055cb1ab8a4943bd112f0fea927d9b";
    private String accessToken;

    @Autowired
    public SpotifyService(SpotifyRepo SpotifyRepo, SpotifyRepo spotifyRepo) {
        this.spotifyRepo = spotifyRepo;
        refreshCache();
    }

    public void refreshCache() {
        layer1IdsCache = getDistinctLayer1();
        layer2IdsCache = getDistinctLayer2();
        layer1ToLayer2MapCache = getLayer1ToLayer2Mapping();
        layer2ToSongsMapCache = getLayer2ToSongsMapping();
    }

    public List<String> getDistinctLayer1() {
        return spotifyRepo.findDistinctLayer1();
    }

    public List<String> getDistinctLayer2() {
        return spotifyRepo.findDistinctLayer2();
    }

    public Map<String, List<String>>  getLayer1ToLayer2Mapping() {
        List<Object[]> pairs = spotifyRepo.findLayer1AndLayer2Pairs();

        // Use set firs to get unique values
        Map<String, Set<String>> setMap = new HashMap<>();
        for (Object[] pair : pairs) {
            String layer1Id = (String) pair[0];
            String layer2Id = (String) pair[1];
            setMap.computeIfAbsent(layer1Id, k -> new HashSet<>()).add(layer2Id);
        }

        // Convert set to list
        Map<String, List<String>> resultMap = new HashMap<>();
        for(Map.Entry<String, Set<String>> entry : setMap.entrySet()){
            resultMap.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
        return resultMap;
    }

    public Map<String, List<SpotifySong>> getLayer2ToSongsMapping() {
        List<String> layer2Ids = spotifyRepo.findDistinctLayer2();
        Map<String, List<SpotifySong>> map = new HashMap<>();
        for (String layer2Id : layer2Ids) {
            List<SpotifySong> songs = spotifyRepo.findSongsByLayer2(layer2Id);
            map.put(layer2Id, songs);
        }
        return map;
    }

    // spotify
    public String getAlbumCoverUrl(String trackId) throws JsonProcessingException {
        // Check if the access token is already obtained and not expired
        if (accessToken == null || isAccessTokenExpired()) {
            // If the access token is not obtained or expired, get a new one
            obtainAccessToken();
        }

        RestTemplate restTemplate = new RestTemplate();

        // Set up headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String spotifyUrl = "https://api.spotify.com/v1/tracks/" + trackId;
        ResponseEntity<String> spotifyResponse = restTemplate.exchange(spotifyUrl, HttpMethod.GET, entity, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(spotifyResponse.getBody());

        // Extract URL of the smallest album cover image
        JsonNode images = rootNode.path("album").path("images");
        String imageUrl = images.get(images.size() - 1).path("url").asText();

        return imageUrl;
    }

    private void obtainAccessToken() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(clientId, clientSecret);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("https://accounts.spotify.com/api/token", request, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(response.getBody());
        accessToken = rootNode.path("access_token").asText();
    }

    private boolean isAccessTokenExpired() {
        // Add logic to check if the access token is expired
        // For example, you can check the expiration time of the token and compare it with the current time
        // Return true if the token is expired, false otherwise
        return true;
    }

    // lastfm (search function not working well)
//    public String getAlbumCoverUrl(String artist, String trackName) {
//        RestTemplate restTemplate = new RestTemplate();
//        String url = "http://ws.audioscrobbler.com/2.0/?method=track.getInfo&api_key=fbf79e70392a0765afbe01a136fbb9af&artist=" + artist + "&track=" + trackName + "&format=json";
//        String response = restTemplate.getForObject(url, String.class);
//
//        JSONObject jsonObj = new JSONObject(response);
//        String imageUrl = "";
//        if (jsonObj.has("track") && !jsonObj.isNull("track")) {
//            JSONObject trackObj = jsonObj.getJSONObject("track");
//            if (trackObj.has("album") && !trackObj.isNull("album")) {
//                imageUrl = trackObj.getJSONObject("album").getJSONArray("image").getJSONObject(3).getString("#text");
//            } else {
//                imageUrl = "https://thisis-images.scdn.co/37i9dQZF1DZ06evO0jjjFK-default.jpg";
//            }
//        } else {
//            imageUrl = "https://thisis-images.scdn.co/37i9dQZF1DZ06evO0jjjFK-default.jpg";
//        }
//
//        return imageUrl;
//    }

    // musicbrainz (somehow too slow)
//    public String getAlbumCoverUrl(String track) throws JsonProcessingException {
//        RestTemplate restTemplate = new RestTemplate();
//        String mbUrl = "https://musicbrainz.org/ws/2/recording?query=recording:" + track + "~&fmt=json";
//        String mbResponse = restTemplate.getForObject(mbUrl, String.class);
//
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode rootNode = mapper.readTree(mbResponse);
//        String releaseId = rootNode.path("recordings").get(0).path("releases").get(0).path("id").asText();
//
//
//        String imageUrl = "";
//        try {
//            String coverArtUrl = "http://coverartarchive.org/release/" + releaseId;
//            String coverArtResponse = restTemplate.getForObject(coverArtUrl, String.class);
//            JsonNode coverArtNode = mapper.readTree(coverArtResponse);
//            imageUrl = coverArtNode.path("images").get(0).path("image").asText();
//        } catch (Exception e){
//            imageUrl = "https://thisis-images.scdn.co/37i9dQZF1DZ06evO0jjjFK-default.jpg";
//        }
//
//        return imageUrl;
//    }
}
