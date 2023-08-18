//treeData = JSON.parse(treeData);
let svgWidth = 1500, svgHeight = 1000;
let margin = {top: 550, right: 70, bottom: 120, left: 200};
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
}).sum(function() { return 1; });

root.y = height; 
tree(root);

let link = svg.selectAll("path.link")
    .data(root.links())
    .enter().append("path")
    .attr("class", "link")
    .attr("d", function(d) {
        return "M" + d.source.x + "," + d.source.y
            + "L" + d.target.x + "," + d.target.y;
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
        if (d.depth === 2) { return 0; }  
        else { return -15; }
    }) 
    .style("text-anchor", function(d) { 
        if (d.depth === 2) { return "middle"; }
        else { return "end";}
    })
    .text(function(d) { return d.data.name; })
    .each(function(d) {
        if (d.depth === 2 && d.data.name.length > 15) {
            wrap(d3.select(this), 85);
        }
    });

function wrap(text, width) {
    text.each(function() {
        var text = d3.select(this),
            words = text.text().split(/\s+/).reverse(),
            word,
            line = [],
            lineNumber = 0,
            lineHeight = 1.2, // ems
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
    
    
    


    
    
    