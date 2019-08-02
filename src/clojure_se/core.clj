;
;  Demonstrates how to start a Helidon server and implement the handlers in Clojure
;  Thanks to Carcigenicate for assistance
;
(ns clojure-se.core
  (:gen-class)
  (:import io.helidon.webserver.Routing
           io.helidon.webserver.Routing$Builder
           io.helidon.webserver.Handler
           io.helidon.webserver.ServerConfiguration
           io.helidon.webserver.ServerRequest
           io.helidon.webserver.ServerResponse
           io.helidon.webserver.WebServer))

(defn -main
  "I am a Helidon app."
  [& args]

  ;
  ;  First define handler(s) using reify Handler and implement accept(req, res) 
  ;
  (let [handler (reify io.helidon.webserver.Handler
                  (^void accept [this ^ServerRequest req, ^ServerResponse res]
                    (println "handled a request")
                    (.send res (str "hello " (.param (.path req) "name")))))

        ; 
        ;  Now we can create the routing and add each handler using the appropriate
        ;  method, in this example we use "any", there are also "get", "put" and so on,
        ;  check the Javadoc for complete list, or use clojure.reflect.  Note that 
        ;  the handler(s) need to be put into an array when the Java method. use
        ;  varargs
        ;
        builder (Routing/builder)
        routing (.get builder "/{name}" (into-array Handler [handler]))

        ;
        ;  Create the server configuration
        ;
        config-builder (ServerConfiguration/builder)
        config (.port config-builder 8080)

        ;
        ;  Create the server
        ;
        server (WebServer/create config routing)]

    ;
    ;  Now we can start the server
    ;
    (.start server)))
