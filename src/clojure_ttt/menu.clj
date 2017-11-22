(ns clojure-ttt.menu
  (:require [clojure-ttt.human-player :refer :all]) 
  (:require [clojure-ttt.computer-player :refer :all]) 
  (:require [clojure-ttt.game :refer :all]) 
  (:require [clojure-ttt.ui :refer :all]))

(defn exit [])

(defn get-move-func []
  (get-choice-from-map
    "Choose player type: (h)uman or (c)omputer"
    "Please choose a valid player type:"
    {"h" get-human-move "c" get-negamax-move}))

(declare main-menu)
(defn make-game []
  (run-game [(get-move-func) (get-move-func)])
  (main-menu))

(defn main-menu []
  (output "Welcome to Tic Tac Toe")
  (apply (get-choice-from-map
           "1) Play a game\n2) Exit"
           "Please choose a valid option:"
           {"1" make-game "2" exit})
         []))
