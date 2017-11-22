(ns clojure-ttt.menu-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.game :refer :all]   
            [clojure-ttt.human-player :refer :all]   
            [clojure-ttt.computer-player :refer :all]   
            [clojure-ttt.ui :refer :all]   
            [clojure-ttt.menu :refer :all])) 

(describe "menu"
          (with-stubs)

          (it "prints welcome message"
              (with-redefs
                [get-choice-from-map (stub :get-choice-from-map)
                 (should=
                   #"Welcome"
                   (with-out-str
                     (main-menu)))]))

          (it "calls get-choice-from-map with [{1 make-game 2 exit}]"
              (with-redefs
                [get-choice-from-map
                 (stub :get-choice-from-map
                       {:return (stub :returned-func)})]
                (main-menu)

                (should-have-invoked
                  :get-choice-from-map
                  {:with
                   ["1) Play a game\n2) Exit"
                    "Please choose a valid option:"
                    {"1" make-game "2" exit}]})

                (should-have-invoked :returned-func))))

(describe "get-player-choice"
          (it "prints player input message"
              (should-contain
                #"(?s)Choose player type: \(h\)uman or \(c\)omputer"
                (with-out-str
                  (with-in-str "h\n" (get-move-func)))))

          (it "gets get-human-move when input is h"
              (should= get-human-move (with-in-str
                             "h\n"
                             (get-move-func))))

          (it "returns get-negamax-move when input is c"
              (should= get-negamax-move (with-in-str
                             "c\n"
                             (get-move-func))))

          (it "prints invalid input message when not h or c"
              (should-contain
                #"(?s)Please choose a valid player type:"
                (with-out-str
                  (with-in-str "r\nh\n" (get-move-func)))))

          (it "keeps asking until h or c"
              (should= get-human-move (with-in-str "r\nh\n" (get-move-func)))))

(describe "make game"
          (with-stubs)

          (it "calls run-game with human move funcs when input is human human"
              (with-redefs
                [run-game (stub :run-game)]
                (with-out-str
                  (with-in-str "h\nh\n" (make-game)))

                (should-have-invoked
                  :run-game
                  {:with [[get-human-move get-human-move]]})))

          (it "calls run-game with human move and negamax move when input is human computer"
              (with-redefs
                [run-game (stub :run-game)]
                (with-out-str
                  (with-in-str "h\nc\n" (make-game)))

                (should-have-invoked
                  :run-game
                  {:with [[get-human-move get-negamax-move]]})))

          (it "calls run-game with negamax move and human move when input is computer human"
              (with-redefs
                [run-game (stub :run-game)]
                (with-out-str
                  (with-in-str "c\nh\n" (make-game)))

                (should-have-invoked
                  :run-game
                  {:with [[get-negamax-move get-human-move]]}))))
