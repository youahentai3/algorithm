import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Offer
{
    public static void main(String[] args)
    {
        int[] a={1,2,3,3,4,4,5};
        ListNode pHead=initList(a);
//        display(pHead);
        pHead=deleteDuplication(pHead);
        display(pHead);
//        System.out.println();
    }

    public static ListNode deleteNode(ListNode head, ListNode tobeDelete)
    {
        if(head==null || tobeDelete==null)
            return null;

        if(tobeDelete.next!=null)
        {
            tobeDelete.val=tobeDelete.next.val;
            tobeDelete.next=tobeDelete.next.next;
        }
        else
        {
            if(head==tobeDelete)
                return null;
            else
            {
                ListNode pre=head;
                while (pre.next!=tobeDelete)
                    pre=pre.next;
                pre.next=null;
                return head;
            }
        }
    }

    private static void display(ListNode head)
    {
        while (head!=null)
        {
            System.out.print(head.val);
            head=head.next;
        }
    }

    private static ListNode initList(int[] a)
    {
        ListNode pHead=new ListNode(a[0]);
        ListNode p=pHead;
        for(int i=1;i<a.length;i++)
        {
            p.next=new ListNode(a[i]);
            p=p.next;
        }
        return pHead;
    }

    public static ListNode deleteDuplication(ListNode pHead)
    {
        if(pHead==null || pHead.next==null)
            return pHead;
        ListNode p=pHead.next;
        if(pHead.val==p.val)
        {
            while (p!=null && p.val==pHead.val)
            {
                p=p.next;
            }
            return deleteDuplication(p);
        }
        else
        {
            pHead.next=deleteDuplication(pHead.next);
            return pHead;
        }
        /*if(pHead==null)
            return null;

        ListNode pre=pHead,p=pHead;
        while (p!=null)
        {
            if(p.next!=null && p.val==p.next.val)
            {
                ListNode tp=p.next;
                while (tp!=null && tp.val==p.val)
                {
                    tp=tp.next;
                }
                if(pre==p)
                {
                    pHead=tp;
                    pre=tp;
                }
                else
                    pre.next=tp;
                p=tp;
            }
            else
            {
                if(pre!=p)
                    pre=pre.next;
                p=p.next;
            }
        }
        return pHead;*/
    }

    static boolean[] visited;

    public static boolean hasPath(char[] matrix, int rows, int cols, char[] str)
    {
        if(str==null || str.length==0)
            return true;
        if(matrix==null || matrix.length==0)
            return false;
        visited=new boolean[matrix.length];
        for(int i=0;i<matrix.length;i++)
        {
            if(matrix[i]==str[0])
            {
                visited[i]=true;
                if(hasCore(matrix,rows,cols,str,i,1))
                    return true;
                visited[i]=false;
            }
        }
        return false;
    }

    private static boolean hasCore(char[] matrix,int rows,int cols,char[] str,int index1,int index2)
    {
        if(index2>=str.length)
            return true;

        int row=index1/cols,col=index1%cols;

        if(col>0 && !visited[index1-1] && matrix[index1-1]==str[index2])
        {
            visited[index1-1]=true;
            if(hasCore(matrix,rows,cols,str,index1-1,index2+1))
                return true;
            visited[index1-1]=false;
        }
        if(row<rows-1 && !visited[index1+cols] && matrix[index1+cols]==str[index2])
        {
            visited[index1+cols]=true;
            if (hasCore(matrix,rows,cols,str,index1+cols,index2+1))
                return true;
            visited[index1+cols]=false;
        }
        if(col<cols-1 && !visited[index1+1] && matrix[index1+1]==str[index2])
        {
            visited[index1+1]=true;
            if (hasCore(matrix,rows,cols,str,index1+1,index2+1))
                return true;
            visited[index1+1]=false;
        }
        if(row>0 && !visited[index1-cols] && matrix[index1-cols]==str[index2])
        {
            visited[index1-cols]=true;
            if (hasCore(matrix,rows,cols,str,index1-cols,index2+1))
                return true;
            visited[index1-cols]=false;
        }
        return false;
    }

    private int minn(int[] array,int be,int en)
    {
        int min=be;
        for(int i=be+1;i<=en;i++)
            if(array[i]<array[min])
                min=i;
        return array[min];
    }

    public int minNumberInRotateArray(int [] array)
    {
        int be=0,en=array.length-1,mid=(be+en)/2;
        if(array[be]<array[en])
            return array[0];

        while (array[be]>=array[en] && be<en-1)
        {
            mid=(be+en)/2;
            if(array[mid]==array[be] && array[mid]==array[en])
                return minn(array,be,en);
            else if(array[mid]>=array[be])
                be=mid;
            else if(array[mid]<=array[en])
                en=mid;
        }
        return array[en];
    }

    public int RectCover(int target)
    {
        if(target<1)
            return 0;
        if(target==1)
            return 1;
        if(target==2)
            return 2;
        return RectCover(target-1)+RectCover(target-2);
    }

    public int JumpFloor(int target)
    {
        if(target<1)
            return 0;
        if(target==1)
            return 1;
        if(target==2)
            return 2;
        return JumpFloor(target-1)+JumpFloor(target-2);
    }

    public int Fibonacci(int n)
    {
        if(n==0)
            return 0;
        else if(n==1 || n==2)
            return 1;
        //n-=2;

        int a=1,b=1;
        while (n>2)
        {
            int te=b;
            b=a+b;
            a=te;
            n--;
        }
        return b;
    }

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node)
    {
        stack1.push(node);
    }

    public int pop()
    {
        if(stack2.empty())
        {
            while (!stack1.empty())
            {
                int temp=stack1.pop();
                stack2.push(temp);
            }
        }
        return stack2.pop();
    }

    public static TreeLinkNode GetNext(TreeLinkNode pNode)
    {
        System.out.println(pNode.val+" "+pNode.next.val+" "+pNode.right.val+" "+pNode.left.val);
        if(pNode==null)

            return null;
        else if(pNode.right!=null)
        {
            TreeLinkNode p=pNode.right;
            while (p.left!=null)
                p=p.left;
            return p;
        }
        else if(pNode.next==null || pNode.next==pNode)
            return null;
        else if(pNode.next.left==pNode)
            return pNode.next;
        else
        {
            TreeLinkNode p=pNode;
            while (p.next!=p && p.next!=null && p!=p.next.left)
            {
                p=p.next;
            }
            if(p.next!=p && p.next!=null)
                return p.next;
            else
                return null;
        }
    }

    public TreeNode reConstructBinaryTree(int [] pre,int [] in)
    {
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<in.length;i++)
            map.put(in[i],i);
        return construct(map,pre,0,pre.length-1,0);
    }
    private TreeNode construct(Map<Integer,Integer> map,int[] pre,int prebe,int preen,int inbe)
    {
        if(prebe>preen)
            return null;
        TreeNode node=new TreeNode(pre[prebe]);
        int index=map.get(pre[prebe]);
        int a=index-inbe;
        node.left=construct(map,pre,prebe+1,a+prebe,inbe);
        node.right=construct(map,pre,a+1+prebe,preen,index+1);

        return node;
    }

    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode)
    {
        ArrayList<Integer> re=new ArrayList<>();
        printA(re,listNode);

        return re;
    }
    private static void printA(ArrayList<Integer> re,ListNode node)
    {
        if(node==null)
            return;
        printA(re,node.next);
        re.add(node.val);
    }
    public static boolean match(char[] str, char[] pattern)
    {
        boolean[][] a=new boolean[pattern.length+1][str.length+1];
        a[0][0]=true;

        for(int i=1;i<a.length;i++)
            a[i][0]=false;
        for(int i=1;i<a[0].length;i++)
            a[0][i]=false;
        for (int i=2;i<a.length;i++)
        {
            if(str[i-1]=='*')
                a[i][0]=a[i-2][0];
        }

        for(int i=1;i<a.length;i++)
        {
            for(int j=1;j<a[i].length;j++)
            {
                if(pattern[i-1]==str[j-1] || pattern[i-1]=='.')
                {
                    a[i][j]=a[i-1][j-1];
                }
                else if(pattern[i-1]=='*')
                {
                    if(pattern[i-2]==str[j-1])
                    {
                        a[i][j]=(a[i-2][j] || a[i-1][j] || a[i][j-1]);
                    }
                    else
                        a[i][j]=a[i-2][j];
                }
            }
        }

        return a[a.length-1][a[0].length-1];
    }

    public static String replaceSpace(StringBuffer str)
    {
        int cou=0;

        for(int i=0;i<str.length();i++)
        {
            if(str.charAt(i)==' ')
                cou++;
        }

        int k1=str.length()-1;
        str.setLength(str.length()+cou*2);
        int k2=str.length()-1;

        while (k1>=0)
        {
            if(str.charAt(k1)!=' ')
            {
                str.replace(k2,k2+1,str.charAt(k1)+"");
                k1--;
                k2--;
            }
            else
            {
                str.replace(k2-2,k2+1,"%20");
                k1--;
                k2-=3;
            }
        }

        return str.toString();
    }

    public static int[] multiply(int[] A)
    {
        if(A==null || A.length==0)
            return null;

        int[] c=new int[A.length];
        int[] b=new int[A.length];
        int[] re=new int[A.length];
        c[0]=A[0];
        b[b.length-1]=A[A.length-1];

        for(int i=1;i<c.length;i++)
        {
            c[i]=c[i-1]*A[i];
            b[b.length-1-i]=A[b.length-1-i]*b[b.length-i];
        }
        for(int i=0;i<re.length;i++)
        {
            int x,y;
            if(i==0)
            {
                x=1;
                y=b[i+1];
            }
            else if(i==re.length-1)
            {
                y=1;
                x=c[i-1];
            }
            else
            {
                x=c[i-1];
                y=b[i+1];
            }

            re[i]=x*y;
        }

        return re;
    }

    public static boolean Find(int target, int [][] array)
    {
        if(array==null || array.length==0)
            return false;

        int i=0,j=array[0].length-1;
        while (j>=0 && i<array.length)
        {
            if(target==array[i][j])
                return true;
            else if(target>array[i][j])
                i++;
            else
                j--;
        }

        return false;
    }
}
