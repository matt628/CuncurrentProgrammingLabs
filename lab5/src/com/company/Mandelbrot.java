package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.swing.JFrame;


public class Mandelbrot extends JFrame {

    private BufferedImage I;
    private double zx, zy, cX, cY, tmp;
    private final int threadNumber = 5;

    public Mandelbrot() {
        super("Mandelbrot Set");
        setBounds(100, 100, 800, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // create pools
        ExecutorService pool = Executors.newFixedThreadPool(threadNumber);
        Set<Pair<Future<BufferedImage>, Integer>> set = new HashSet<>();

        // dispense work
        int sectionHeight = getHeight()/threadNumber;
        for(int i = 0; i < threadNumber; i++) {
            int fromHeight = i*sectionHeight;
            int toHeight = (i == threadNumber - 1) ? getHeight() : (i+1)*sectionHeight; // last thread compute the rest of image
            Callable<BufferedImage> callable = new MandelbrotComputation(fromHeight, toHeight, getWidth());
            Future<BufferedImage> future = pool.submit(callable);
            set.add(new Pair<>(future, i));
        }

        I = concatanateImages(set);


    }

    private BufferedImage concatanateImages(Set<Pair<Future<BufferedImage>, Integer>> set) {
        I = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = I.createGraphics();
        int sectionHeight = getHeight()/threadNumber;

        try {
            for(var pairFutureImage_Positon: set){
                graphics.drawImage(pairFutureImage_Positon.getLeft().get(), null, 0, pairFutureImage_Positon.getRight()*sectionHeight);
            }
        } catch (Exception e) {
            System.out.println("There was an exception thrown: " + e);
            e.printStackTrace();
        }
        return I;
    }


    public static void main(String[] args) {
        new Mandelbrot().setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(I, 0, 0, this);
    }
}

//    private BufferedImage compute(int fromHeight, int toHeight, int width){
//        BufferedImage bufferedImage = new BufferedImage(width, toHeight-fromHeight, BufferedImage.TYPE_INT_RGB);
//        for (int y = fromHeight; y < toHeight; y++) {
//            for (int x = 0; x < width; x++) {
//                zx = zy = 0;
//                cX = (x - 400) / ZOOM;
//                cY = (y - 300) / ZOOM;
//                int iter = MAX_ITER;
//                while (zx * zx + zy * zy < 4 && iter > 0) {
//                    tmp = zx * zx - zy * zy + cX;
//                    zy = 2.0 * zx * zy + cY;
//                    zx = tmp;
//                    iter--;
//                }
//                bufferedImage.setRGB(x, y-fromHeight, iter | (iter << 8));
//            }
//        }
//        return bufferedImage;
//    }