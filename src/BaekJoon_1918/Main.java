package BaekJoon_1918;

import java.util.EmptyStackException;
import java.util.Scanner;

public class Main {
    public static String[] array={"+","-","/","*","(",")"};

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String val = "";
        String userInput=sc.nextLine();

        val=parseString(userInput);
        System.out.println(val);
    }
    public static String parseString(String str){
        StringStack st = new StringStack();
        StringBuilder temp = new StringBuilder("");

        for(int i=0;i<str.length();i++){
            int sw=0;

            for(String j : array){
                if(j.equals(String.valueOf(str.charAt(i)))){
                    sw=1;
                    if(j.equals("(")){
                        pushStack(st,null,j);
                    }else if(j.equals(")")){
                        popStack(st,temp);
                    }else{
                        pushStack(st,temp,j);
                    }
                }
            }
            if(sw==0){
                temp.append(str.charAt(i));
            }

        }

        while(!st.isEmpty()){
            temp.append(st.pop());
        }
        return temp.toString();
    }
    public static void pushStack(StringStack st,StringBuilder temp,String value){
        if(st.isEmpty() || value.equals("(")) st.push(value);
        else{
            int length = st.size();

            if(value.equals("+")||value.equals("-")){
                if(st.getIndex(length-1).equals("(")){
                    st.push(value);
                }else{
                    while(!st.isEmpty()){
                        if(st.peek().equals("(")) break;
                        temp.append(st.pop());
                    }
                    st.push(value);
                }

            }else if(value.equals("*")||value.equals("/")){
                if(st.getIndex(length-1).equals("+")||st.getIndex(length-1).equals("-")){
                    st.push(value);
                }else{
                    if(st.getIndex(length-1).equals("(")){
                        st.push(value);
                    }else {
                        temp.append(st.pop());
                        st.push(value);
                    }
                }
            }
        }
    }

    public static void popStack(StringStack st,StringBuilder str){
        for(;;){
            if(st.isEmpty()) break;
            String a = st.pop();
            if(a.equals("(")) break;
            str.append(a);
        }
    }

}



class StringStack implements Cloneable
{
    private String[ ] data;
    private int manyItems;

    public StringStack( )
    {
        final int INITIAL_CAPACITY = 10;
        manyItems = 0;
        data = new String[INITIAL_CAPACITY];
    }

    public StringStack(int initialCapacity)
    {
        if (initialCapacity < 0)
            throw new IllegalArgumentException
                    ("initialCapacity too small " + initialCapacity);
        manyItems = 0;
        data = new String[initialCapacity];
    }

    public Object clone( )
    {  // Clone an StringStack.
        StringStack answer;

        try
        {
            answer = (StringStack) super.clone( );
        }
        catch (CloneNotSupportedException e)
        {
            throw new RuntimeException
                    ("This class does not implement Cloneable");
        }

        answer.data = (String [ ]) data.clone( );

        return answer;
    }

    public void ensureCapacity(int minimumCapacity)
    {
        String biggerArray[ ];

        if (data.length < minimumCapacity)
        {
            biggerArray = new String[minimumCapacity];
            System.arraycopy(data, 0, biggerArray, 0, manyItems);
            data = biggerArray;
        }
    }

    public int getCapacity( )
    {
        return data.length;
    }
    public boolean isEmpty( )
    {
        return (manyItems == 0);
    }
    public String peek( )
    {
        if (manyItems == 0)
            // EmptyStackException is from java.util and its constructor has no argument.
            throw new EmptyStackException( );
        return data[manyItems-1];
    }
    public String pop( )
    {
        if (manyItems == 0)
            // EmptyStackException is from java.util and its constructor has no argument.
            throw new EmptyStackException( );
        return data[--manyItems];
    }
    public void push(String item)
    {
        if (manyItems == data.length)
        {
            // Int the capacity and add 1; this works even if manyItems is 0. However, in
            // case that manyItems*2 + 1 is beyond Integer.MAX_VALUE, there will be an
            // arithmetic overflow and the bag will fail.
            ensureCapacity(manyItems*2 + 1);
        }
        data[manyItems] = item;
        manyItems++;
    }
    public int size( )
    {
        return manyItems;
    }
    public void trimToSize( )
    {
        String trimmedArray[ ];

        if (data.length != manyItems)
        {
            trimmedArray = new String[manyItems];
            System.arraycopy(data, 0, trimmedArray, 0, manyItems);
            data = trimmedArray;
        }
    }
    public String getIndex(int i){
        return data[i];
    }
}
