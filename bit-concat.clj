;; We are looking for an integer whose bit-representation appended to the
;; bit representation of 43046721 is the bit representation of
;; 2888816585588551.
;;
;; Can you help us out here?
;;
;; Example
;; 5 (which is '101' in base 2) appended to 3 (which is '11' in base 2) is
;; 29, so the number we need to append to the bit representation of 3 to get
;; 29 is 5.

(use 'clojure.string)

(Integer/parseInt
 (replace-first (Long/toBinaryString 2888816585588551)
                (Long/toBinaryString 43046721) "") 2)

;; => 40353607
