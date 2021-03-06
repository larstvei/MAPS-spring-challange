;; This image is the poster for the MAPS Spring Challenge as shown on the
;; IFI info screens. The background is a nice shade of green, namely RGB
;; #5B9836. Somewhere hidden in this image is the portrait of a famous
;; celebrity, painted in a slightly different shade of green, namely RGB
;; #5A9836.
;;
;; We want to know the nickname (which typically is used as his/her first
;; name), in lower case, of this celebrity. This nickname is also hidden in
;; the image below the portrait, for your convenience.

;; Our original solution was to open the image in paint, and used the bucket
;; on the background. This is how one could emulate bucket with Clojure:

(ns maps-spring-challenge.image
  (:require [clojure.java.io :as io]
            [seesaw.core :refer :all])
  (:import javax.imageio.ImageIO)
  (:import java.awt.image.BufferedImage))

(let [image (-> "spring.png" io/resource io/file ImageIO/read)
      [w h] [(.getWidth image) (.getHeight image)]
      needle (unchecked-int 0xFFFF5A9836)
      rgb (for [x (range w) y (range h)] [x y (.getRGB image x y)])]
  (doseq [[x y c] rgb]
    (when (= c needle)
      (.setRGB image x y (unchecked-int 0xFFFF69B4))))
  (let [img (.getScaledInstance image (/ w 2) (/ h 2)
             BufferedImage/SCALE_DEFAULT)]
   (-> (frame :content (label :icon img)) pack! show!)))
