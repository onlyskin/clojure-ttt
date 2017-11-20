(ns clojure-ttt.game
  (:require [clojure-ttt.ui :refer :all])
  (:require [clojure-ttt.board :refer :all]))

(defn- get-move-function [move-functions, depth]
  (as-> [depth] n
    (mod n 2)
    (get n move-functions)))

(defn- play-turn [move-functions depth board]
  (output-board board)
  (cond
    (game-over? board) (output-game-result board)
    :else (do
            (let [get-move (get move-functions (mod depth 2))]
              (->> board
                   (get-move)
                   (play-on-board board)
                   (play-turn move-functions (inc depth)))))))

(defn run-game [move-functions]
  (play-turn move-functions 0 (make-board)))

