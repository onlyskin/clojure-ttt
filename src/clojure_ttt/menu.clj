(ns clojure-ttt.menu
  (:require [clojure-ttt.human-player :refer :all]) 
  (:require [clojure-ttt.computer-player :refer :all]) 
  (:require [clojure-ttt.game :refer :all]) 
  (:require [clojure-ttt.ui :refer :all]))

(defn exit [])

(defn- move-func-from-choice [choice]
  ({"h" get-human-move "c" get-negamax-move} choice))

(defn make-game []
  (run-game [(move-func-from-choice (input-player))
             (move-func-from-choice (input-player))]))

(defn- menu-map [choice]
  ({"1" make-game "2" exit} choice))

(defn main-menu []
  (apply (menu-map (get-choice ["1" "2"])) []))

