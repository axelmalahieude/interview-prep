/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 * 
 * Given a binary tree, return the vertical order traversal of its nodes values.
 * For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).
 * Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, 
 * we report the values of the nodes in order from top to bottom (decreasing Y coordinates).
 * If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.
 * Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.
 * 
 * Ex
 * Input: [3,9,20,null,null,15,7]
 * Output: [[9],[3,15],[20],[7]]
 * 
 * Given a tree with n nodes,
 * Traverse once through tree to make a hashmap that hashes the x position to the node's value and y position (O(n))
 * Get the hashmap's keys and sort (O(nlogn))
 * For each key,
 *  Sort the associated list of (node, y pos) according to y pos (and then node value if y's are equal)
 * Return the list of lists of node values, where list[0][0] = topmost node value in the leftmost vertical line of nodes
 */
class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        // map x value -> list of (node, y value)s in that position
        HashMap<Integer, List<Pair<Integer, Integer>>> positions = new HashMap<>();
        findPositions(root, 0, 0, positions);
        List<Integer> keys = new ArrayList<>(positions.keySet());
        keys.sort(null);
        
        List<List<Integer>> output = new ArrayList<>();
        for (int key : keys) {
            List<Pair<Integer, Integer>> nodesAtPos = positions.get(key);
            nodesAtPos.sort(new Comparator<Pair<Integer, Integer>>() {
                public int compare(Pair<Integer, Integer> first, Pair<Integer, Integer> second) {
                    if (first.getValue() > second.getValue()) return -1;
                    else if (first.getValue() == second.getValue()) {
                        if (first.getKey() < second.getKey()) return -1;
                        else return 1;
                    }
                    else return 1;
                }
            });
            List<Integer> sublist = new ArrayList<>();
            for (Pair<Integer, Integer> pair : nodesAtPos) {
                sublist.add(pair.getKey());
            }
            output.add(sublist);
        }
        return output;
    }
    
    private void findPositions(TreeNode node, int x, int y, HashMap<Integer, List<Pair<Integer, Integer>>> map) {
        if (node == null) {
            return;
        }
        
        // add current node to map
        List<Pair<Integer, Integer>> nodesAtPos = map.get(x);
        if (nodesAtPos == null) {
            nodesAtPos = new ArrayList<>();
        }
        nodesAtPos.add(new Pair(node.val, y));
        map.put(x, nodesAtPos);
        
        // recurse on children
        findPositions(node.left, x - 1, y - 1, map);
        findPositions(node.right, x + 1, y - 1, map);
    }
}