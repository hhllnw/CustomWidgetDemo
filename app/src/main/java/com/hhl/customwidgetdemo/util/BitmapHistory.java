package com.hhl.customwidgetdemo.util;

import android.graphics.Bitmap;

import java.util.Stack;

/**
 * Created by hhl on 2017/11/28.
 * Stack 是java中定义的栈结构，后进先出；
 * push()方法表示压栈；
 * pop()表示出栈，删除栈顶元素，同时返回被删除的元素；
 * peek()是直接取栈顶元素，但是并不删除
 */

public class BitmapHistory {

    private static BitmapHistory history;
    private static Stack<Bitmap> stack;

    public BitmapHistory() {
        if (stack == null) {
            stack = new Stack<>();
        }
    }

    public static BitmapHistory getHistory() {
        if (history == null) {
            history = new BitmapHistory();
        }
        return history;
    }

    /**
     * 将bitmap进栈
     *
     * @param bitmap
     */
    public void pushBitmap(Bitmap bitmap) {

        int size = stack.size();
        if (size >= 5) {
            Bitmap bmp = stack.pop();
            if (!bmp.isRecycled()) {
                bmp.recycle();
                System.gc();
                bmp = null;
            }
        }
        stack.push(bitmap);
    }

    public static Stack<Bitmap> getStack() {
        return stack;
    }

    public static void setStack(Stack<Bitmap> stack) {
        BitmapHistory.stack = stack;
    }

    public void clearStack() {
        if (stack != null && stack.size() > 0) {
            stack.clear();
            stack = null;
        }
    }
}
