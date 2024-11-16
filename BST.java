import java.util.*;
import java.util.List;
public class BST {
    static class Node{
        int data;
        Node left;
        Node right;
        Node(int data) {
            this.data = data;
        }
        public  static Node build(int val , Node root) {
            if(root ==  null) {
                root = new Node(val);
                return root;
            }
            if(root.data > val) {
                //left subtree
                root.left = build(val,root.left);
            }else{
                root.right = build(val,root.right);
            }
            return root;
        }
        public static void inorder(Node root) {
            if(root == null) {
                return;
            }
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
        public static boolean search(int value,Node root) {
            if(root == null) {
                return false;
            }
            if(root.data == value) {
                return true;
            } else if (root.data < value) {
                return search(value,root.right);
            }else{
                return search(value,root.left);
            }

        }
        public static Node delete(Node root, int key) {
            if(root.data > key) {
                root.left = delete(root.left,key);
            }
            else if(root.data < key) {
                root.right = delete(root.right,key);
            }else {
                //no child
                if(root.left == null && root.right == null) {
                    return null;
                }
                if(root.left == null) {
                    return root.right;
                }                                     //for single child
                if(root.right == null) {
                    return root.left;
                }
                Node is = findInorderSuccesoro(root.right);   //for two childs
                root.data = is.data;
                root.right = delete(root.right,key);

            }
            return root;
        }
        public static Node findInorderSuccesoro(Node root) {
            while (root.left != null) {
                root = root.left;
            }
            return root;
        }
        public static void printRange(Node root,int min , int max) {
            if(root == null) {
                return;
            }
            if(min <= root.data && max >= root.data) {
                printRange(root.left, min, max);
                System.out.print(root.data + " ");
                printRange(root.right, min, max);
            }else if( max <= root.data ) {
                printRange(root.left, min, max);
            }else{
                printRange(root.right,min,max);
            }
        }
        public static void printpath(Node root,ArrayList<Integer> path) {
            if(root == null) {
                return;
            }
            path.add(root.data);
            if(root.left == null && root.right == null ){
                System.out.print("\npath  : ");
                path.forEach(i ->{
                    System.out.print(i +" ");
                } );
                System.out.println();
            }else {
                printpath(root.left,path);
                printpath(root.right,path);
            }
            path.remove(path.size() - 1);
        }
        public static int height(Node root) {
            if (root == null) {
                return 0;
            }
            int left = height(root.left);
            int right = height(root.right);
            return Math.max(left,right) + 1;
        }
        public static int sumAtLevel(Node root,int level,int curlevel) {

            if (root == null) {
                return 0;
            }
            if(level == curlevel) {
                return root.data;
            }
            int left = sumAtLevel(root.left,level,curlevel + 1);
            int right = sumAtLevel(root.right,level,curlevel + 1);
            return left + right;
        }
        public static int findLevel(Node root, int element,int level) {
            if(root == null) {
                return 0;
            }
            if(root.data == element) {
                return level;
            }
            int leftlevel = findLevel(root.left, element, level + 1);
            if(leftlevel != 0) {
                return leftlevel;
            }

            int rightlevel= findLevel(root.right, element, level + 1);
            return rightlevel;
        }
    }
    public static void main(String []args)  {
        int values[] = {5, 1, 3, 4, 2, 7};
        Node root = null;
        for (int v:values) {
            root = Node.build(v,root);
        }
        System.out.println("root is  : " + root.data);
        System.out.println("inorder traversal : ");
        Node.inorder(root);
        if(Node.search(1,root)){
            System.out.println("\nfound");
        }else {
            System.out.println("\nnot found");
        }
        //Node.delete(root,4);
        Node.inorder(root);
        System.out.println(" \nbetween the range : ");
        Node.printRange(root,0,5);
        ArrayList<Integer> path = new ArrayList<>();
        Node.printpath(root,path);

        System.out.println("height of the tree is : " + Node.height(root));
        System.out.println("sum at level : " + Node.sumAtLevel(root,3,0));
        System.out.println("element level : " + Node.findLevel(root,5,0));
    }
}

/*
     5
    / \
   1   7
    \
     3
    / \
   2   4
*/
