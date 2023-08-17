treeData = JSON.parse(treeData);
let svgWidth = 1300, svgHeight = 700;  // Adjusted canvas size for more space
let margin = {top: 200, right: 70, bottom: 200, left: 70}; // Increased margin
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
        if (d.depth === 2) { return 0; } 
        else { return 3; }
    })
    .attr("x", function(d) { 
        if (d.depth === 2) { return -190; }  
        else { return d.children ? -15 : 15; }
    }) 
    .style("text-anchor", function(d) { 
        return d.children ? "end" : "start";
    })
    .attr("transform", function(d) {
        if (d.depth === 2) { return "rotate(-90)"; }
        else { return null; }
    })
    .text(function(d) { return d.data.name; });
