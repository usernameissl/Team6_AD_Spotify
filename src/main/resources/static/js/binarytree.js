let treeData = [[$,{treeData}]];

let svgWidth = 600, svgHeight = 400;
let margin = {top: 20, right: 120, bottom: 20, left: 120};
let width = svgWidth - margin.right - margin.left;
let height = svgHeight - margin.top - margin.bottom;

let svg = d3.select("#tree").append("svg")
    .attr("width", svgWidth)
    .attr("height", svgHeight)
    .append("g")
    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

let tree = d3.tree().size([height, width]);

let root = d3.hierarchy(treeData, function(d) {
    return [d.left, d.right];
});

tree(root);

let link = svg.selectAll("path.link")
    .data(root.descendants().slice(1))
    .enter().append("path")
    .attr("class", "link")
    .attr("d", function(d) {
        return "M" + d.y + "," + d.x
             + "C" + (d.y + d.parent.y) / 2 + "," + d.x
             + " " + (d.y + d.parent.y) / 2 + "," + d.parent.x
             + " " + d.parent.y + "," + d.parent.x;
    });

let node = svg.selectAll("g.node")
    .data(root.descendants())
    .enter().append("g")
    .attr("class", function(d) { 
        return "node" + 
            (d.children ? " node--internal" : " node--leaf"); })
    .attr("transform", function(d) { 
        return "translate(" + d.y + "," + d.x + ")"; });

node.append("circle")
    .attr("r", 10);

node.append("text")
    .attr("dy", 3)
    .attr("x", function(d) { 
        return d.children ? -15 : 15; })
    .style("text-anchor", function(d) { 
        return d.children ? "end" : "start"; })
    .text(function(d) { return d.data.name; });