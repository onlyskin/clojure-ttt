(ns clojure-ttt.computer-player-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.computer-player :refer :all]))

(defn- vec-for-string [board-string]
  (clojure.string/split board-string #""))

(describe "get-computer-move"
          (it "returns random valid position"
              (should= 1 (get-computer-move (vec-for-string " OXOOXXXO"))))) 
