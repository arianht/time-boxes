package com.CorGaming.framework;

public interface Graphics {
    public static enum PixmapFormat {
        ARGB8888, ARGB4444, RGB565
    }

    public Pixmap newPixmap(String fileName, PixmapFormat format);

    public void clear(int color);

    public void drawPixel(int x, int y, int color);

    public void drawLine(int x, int y, int x2, int y2, int color);

    public void drawRect(int x, int y, int width, int height, int color, boolean fill);

    public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY,
            int srcWidth, int srcHeight);

    public void drawPixmap(Pixmap pixmap, int x, int y);
    
    public void drawPixmap(Pixmap pixmap, int x, int y, int w, int h,
    		int srcX, int srcY, int srcWidth, int srcHeight);
    
    public void drawText(String text, int x, int y, int color, int size);
    
    public void drawCircle(int cx, int cy, int radius, int color, boolean fill);

    public int getWidth();

    public int getHeight();
}
