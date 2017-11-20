(ns clojure-ttt.core
  (:require [clojure-ttt.game :refer :all])
  (:require [clojure-ttt.human-player :refer :all])
  (:require [clojure-ttt.computer-player :refer :all])
  )

(defn -main [& args]
  (run-game [get-human-move get-negamax-move]))
