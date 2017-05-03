
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/zlatan/Downloads/play-scala-starter-example/conf/routes
// @DATE:Wed May 03 16:20:05 CEST 2017


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
