(ns clojure-ttt.board)

(defn make-board [] (vec (repeat 9 " ")))

(defn play-on-board
  [board, position, marker]
  (assoc board (- position 1) marker))
