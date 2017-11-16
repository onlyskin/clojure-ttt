(ns clojure-ttt.game
  (:require [clojure-ttt.ui :refer :all])
  (:require [clojure-ttt.board :refer :all]))

(defn- play-turn [board]
  (output-board board)
  (cond
    (game-over? board) (output-game-result board)
    :else (do
            (->> (input-integer)
                 (play-on-board board)
                 (play-turn)))))

(defn run-game []
  (play-turn (make-board)))
