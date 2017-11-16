(ns clojure-ttt.game
  (:require [clojure-ttt.ui :refer :all])
  (:require [clojure-ttt.board :refer :all]))

(declare play-turn)
(defn run-game []
  (play-turn (make-board)))

(defn- play-turn [board]
  (cond
    (game-over? board) (output "over")
    :else (do
            (output-board board)
            (play-turn
              (play-on-board board (input-integer)))))
  )
