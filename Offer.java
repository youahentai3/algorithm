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

    public static ListNode FindKthToTail(ListNode head,int k)
    {
        if(head==null || k<1)
            return null;
        ListNode p1=head,p2=head;
        for(int i=0;i<k-1;i++)
        {
            p2=p2.next;
            if(p2==null)
                return null;
        }
        while (p2.next!=null)
        {
            p1=p1.next;
            p2=p2.next;
        }
        return p1;
    }

    public static void reOrderArray(int [] array)
    {
        int be=0,en=array.length-1;

        while (be<en)
        {
            while (be<en && (array[be]&1)!=0)
                be++;
            while (be<en && (array[en]&1)==0)
                en--;
            if(be<en)
            {
                int te=array[be];
                array[be]=array[en];
                array[en]=te;
            }
        }
    }

    static boolean flag;
    public static boolean isNumeric(char[] str)
    {
        if(str==null)
            return false;
        int k=check2(str,0);
        boolean nu=flag;

        if(k<str.length && str[k]=='.')
        {
            k++;
            k=check1(str,k);
            nu=(flag || nu);
        }
        if(k<str.length && (str[k]=='e' || str[k]=='E'))
        {
            k++;
            k=check2(str,k);
            nu=(flag && nu);
        }

        return (nu && k==str.length);
    }

    private static int check1(char[] str,int k)
    {
        int te=k;
        while (k<str.length && str[k]>='0' && str[k]<='9')
            k++;

        if(te==k)
            flag=false;
        else
            flag=true;
        return k;
    }

    private static int check2(char[] str,int k)
    {
        if(k<str.length && (str[k]=='+' || str[k]=='-'))
            k++;
        return check1(str,k);
    }

    public static boolean match(char[] str, char[] pattern)
    {
        if(str==null || pattern==null)
            return false;

        return matchCore(str,pattern,0,0);
    }

    private static boolean matchCore(char[] str,char[] pattern,int k1,int k2)
    {
        if(k1==str.length && k2==pattern.length)
            return true;
        if((k1!=str.length && k2==pattern.length))
            return false;

        if(k2<pattern.length-1 && pattern[k2+1]=='*')
        {
            if(k1<str.length && (str[k1]==pattern[k2] || pattern[k2]=='.'))
            {
                return (matchCore(str,pattern,k1,k2+2) || matchCore(str, pattern, k1+1, k2+2) || matchCore(str, pattern, k1+1, k2));
            }
            else
                return matchCore(str, pattern, k1, k2+2);
        }
        else
        {
            if(k1<str.length && (str[k1]==pattern[k2] || pattern[k2]=='.'))
                return matchCore(str, pattern, k1+1, k2+1);
            else
                return false;
        }
    }

    public static void printMax(int n)
    {
        char[] ch=new char[n];
        printM(ch,0);
    }

    private static void printM(char[] ch,int k)
    {
        if (k == ch.length)
        {
            System.out.print(new String(ch)+" ");
            return;
        }

        for(int i=0;i<10;i++)
        {
            ch[k]=(char)('0'+i);
            printM(ch,k+1);
        }
    }

    public static double Power(double base, int exponent)
    {
        if(Math.abs(base-0.0)<1e-5)
        {
            if (exponent <= 0.0) return 0.0;
            else return 0.0;
        }
        if(exponent==0)
            return 1.0;
        boolean flag=true;
        if(exponent<0.0)
        {
            flag=false;
            exponent=Math.abs(exponent);
        }
        double[] a=new double[exponent];
        a[0]=1.0;
        a[1]=base;

        double te=pow(base,exponent,a);
        if(!flag)
            te=1.0/te;

        return te;
    }

    private static double pow(double base,int exponent,double[] a)
    {
        if(exponent==0)
            return 1;
        if(exponent==1)
            return base;

        int b=(exponent>>1);
        if(Math.abs(a[b]-0.0)<1e-5)
            a[b]=pow(base,b,a);
        if((exponent&1)==0)
            return a[b]*a[b];
        else
            return a[b]*a[b]*base;
    }

    public static int NumberOf1(int n)
    {
        int flag=1,cou=0;

        while (flag!=0)
        {
            if((n&flag)!=0)
                cou++;
            flag<<=1;
        }

        return cou;
    }

    public static int cutRope(int target)
    {
        if(target<2)
            return 0;
        else if(target==2)
            return 1;
        int[] products=new int[target+1];

        products[0]=0;
        products[1]=1;
        products[2]=2;
        //products[3]=2;
        for(int i=3;i<products.length;i++)
        {
            int max=0;
            for(int j=1;j<=i/2;j++)
            {
                int te=products[j]*products[i-j];
                if(te>max)
                    max=te;
            }
            if(i<products.length-1 && max<i)
                max=i;
            products[i]=max;
        }
        return products[target];
    }

    static boolean[] visited;
    public static int movingCount(int threshold, int rows, int cols)
    {
        visited=new boolean[rows*cols];
        int re=0;

        re=moving(threshold,rows,cols,0,0);

        return re;
    }

    private static int moving(int threshold,int rows,int cols,int k1,int k2)
    {
        int re=0,index=k1*cols+k2;
        int a=getD(k1),b=getD(k2);
        if(!visited[index] && a+b<=threshold)
        {
            visited[index]=true;
            re++;
        }
        else
            return re;

        if(k2>0)
            re+=moving(threshold, rows, cols, k1, k2-1);
        if(k1<rows-1)
            re+=moving(threshold, rows, cols, k1+1, k2);
        if(k2<cols-1)
            re+=moving(threshold, rows, cols, k1, k2+1);
        if(k1>0)
            re+=moving(threshold, rows, cols, k1-1, k2);
        return re;
    }

    private static int getD(int a)
    {
        int re=0;

        while (a>0)
        {
            re+=(a%10);
            a/=10;
        }

        return re;
    }

    public static ListNode deleteNode(ListNode head, ListNode tobeDelete)
    {
        if(head==null || tobeDelete==null)
            return null;

        if(tobeDelete.next!=null)
        {
            tobeDelete.val=tobeDelete.next.val;
            tobeDelete.next=tobeDelete.next.next;
            return head;
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

  //  static boolean[] visited;

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
