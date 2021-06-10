(ns fr.grunwald.async-ex
  #?(:clj
     (:require [clojure.core.async]
               [net.cgrand.macrovich :as macros])))

(defn throw-err [e]
  (when (instance? #?(:clj Throwable :cljs js/Error) e) (throw e))
  e)

#?(:clj
   (defmacro <?
     "Like <! but throws errors."
     [ch]
     `(macros/case
        :cljs (throw-err (cljs.core.async/<! ~ch))
        :clj (throw-err (clojure.core.async/<! ~ch)))))

#?(:clj
   (defn <??
     "Like <!! but throws errors."
     [ch]
     (throw-err (clojure.core.async/<!! ch))))

#?(:clj
   (defmacro go-try+
     "Like go but catches the first thrown error and returns it."
     [& body]
     `(macros/case
        :cljs (cljs.core.async/go
                (try
                  ~@body
                  (catch js/Error e# e#)))
        :clj (clojure.core.async/go
               (try
                 ~@body
                 (catch Throwable t# t#))))))
