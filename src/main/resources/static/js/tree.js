treeData = JSON.parse(treeData);
let svgWidth = 1300, svgHeight = 700;
let margin = {top: 220, right: 70, bottom: 220, left: 70};
let width = svgWidth - margin.right - margin.left;
let height = svgHeight - margin.top - margin.bottom;

let svg = d3.select("#tree").append("svg")
    .attr("width", svgWidth)
    .attr("height", svgHeight)
    .append("g")
    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

let tree = d3.tree().size([width, height]);

let root = d3.hierarchy(treeData, function(d) {
    return d.children;
});

tree(root);

let link = svg.selectAll("path.link")
    .data(root.links())
    .enter().append("path")
    .attr("class", "link")
    .attr("d", function(d) {

        let offset = (d.target.y - d.source.y) / 3;
        return "M" + d.source.x + "," + d.source.y
            + "C" + d.source.x + "," + (d.source.y + offset)
            + " " + d.target.x + "," + (d.source.y + offset)
            + " " + d.target.x + "," + d.target.y;
    });

let node = svg.selectAll("g.node")
    .data(root.descendants())
    .enter().append("g")
    .attr("class", function(d) { 
        return "node" + 
            (d.children ? " node--internal" : " node--leaf"); })
    .attr("transform", function(d) { 
        return "translate(" + d.x + "," + d.y + ")"; });

node.append("circle")
    .attr("r", 10);

node.append("text")
    .attr("dy", function(d) {
        if (d.depth === 2) { return 30; } 
        else { return 3; }
    })
    .attr("x", function(d) { 
        if (d.depth === 2) { return -30; }  
        else { return d.children ? -15 : 15; }
    }) 
    .style("text-anchor", function(d) { 
        if (d.depth === 2) { return "start"; }
        else { return d.children ? "end" : "start"; }
    })
    .text(function(d) { return d.data.name; })
    .each(function(d) {
        if (d.depth === 2 && d.data.name.length > 25) {
            wrap(d3.select(this), 120);
        }
    });

function wrap(text, width) {
    text.each(function() {
        var text = d3.select(this),
            words = text.text().split(/\s+/).reverse(),
            word,
            line = [],
            lineNumber = 0,
            lineHeight = 1.5, // ems
            y = text.attr("y"),
            x = text.attr("x"), // get x position
            tspan = text.text(null).append("tspan").attr("x", x).attr("y", y).attr("dy", "2.7em");
        while (word = words.pop()) {
            line.push(word);
            tspan.text(line.join(" "));
            if (tspan.node().getComputedTextLength() > width) {
                line.pop();
                tspan.text(line.join(" "));
                line = [word];
                tspan = text.append("tspan").attr("x", x).attr("y", y).attr("dy", (++lineNumber * lineHeight) + "em").text(word);
            }
        }
    });
}
    
    
    


    
    
    