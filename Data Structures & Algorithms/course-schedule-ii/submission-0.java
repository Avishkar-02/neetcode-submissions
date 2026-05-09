class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        List<List<Integer>>graph=new ArrayList<>();

        for(int i=0;i<numCourses;i++){
            graph.add(new ArrayList<>());
        }

        for(int[]pair:prerequisites){
            
            int prereq=pair[1];
            int course=pair[0];

            graph.get(prereq).add(course);
        }
        
        boolean[]visited=new boolean[numCourses];
        boolean[]dfsVisited=new boolean[numCourses];

        Stack<Integer>stack=new Stack<>();

        for(int i=0;i<numCourses;i++){
            if(!visited[i]){
                if(hasCycle(i,graph,visited,dfsVisited,stack)){
                    return new int[0];
                }
            }
        }

        int[]result=new int[numCourses];
        int idx=0;
        while(!stack.isEmpty()){
            result[idx++]=stack.pop();
        }

        return result;
    }

    private boolean hasCycle(int node,List<List<Integer>>graph,boolean[]visited,boolean[]dfsVisited, Stack<Integer>stack){

        visited[node]=true;
        dfsVisited[node]=true;

        for(int neighbor:graph.get(node)){
            
            if(!visited[neighbor]){
                if(hasCycle(neighbor,graph,visited,dfsVisited,stack)){
                    return true;
                }
            }else if(dfsVisited[neighbor]){
                return true;
            }
        }

        dfsVisited[node]=false;
        stack.push(node);
        return false;
    }
}
