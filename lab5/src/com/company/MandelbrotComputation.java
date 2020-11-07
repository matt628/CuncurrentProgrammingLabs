package com.company;

import java.awt.image.BufferedImage;
import java.util.concurrent.Callable;

public class MandelbrotComputation implements Callable<BufferedImage> {
    private final int MAX_ITER = 400;
    private final double ZOOM = 150;
    private double zx, zy, cX, cY, tmp;
    private final int fromHeight, toHeight, width;

    public MandelbrotComputation(int fromHeight, int toHeight, int width) {
        this.fromHeight = fromHeight;
        this.toHeight = toHeight;
        this.width = width;
    }

    private BufferedImage compute(){
        BufferedImage I = new BufferedImage(width, toHeight-fromHeight, BufferedImage.TYPE_INT_RGB);
        for (int y = fromHeight; y < toHeight; y++) {
            for (int x = 0; x < width; x++) {
                zx = zy = 0;
                cX = (x - 400) / ZOOM;
                cY = (y - 300) / ZOOM;
                int iter = MAX_ITER;
                while (zx * zx + zy * zy < 4 && iter > 0) {
                    tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    iter--;
                }
                I.setRGB(x, y-fromHeight, iter | (iter << 8));
            }
        }
        return I;
    }

    @Override
    public BufferedImage call() throws Exception {
        return compute();
    }
}
