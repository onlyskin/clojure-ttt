(ns clojure-ttt.computer-player
  (:require [clojure-ttt.ui :refer :all])
  (:require [clojure-ttt.board :refer :all]) )

(defn get-computer-move [board]
  (->> board
       (available-moves)
       (rand-nth)))
