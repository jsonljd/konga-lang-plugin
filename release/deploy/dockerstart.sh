#!/bin/sh
#
# Start/Stop the server.
#

CLASSPATH="conf/"
for jarfile in `ls ./lib/.`; do
   CLASSPATH="${CLASSPATH}:lib/$jarfile"
done

loadJavaOpts () {
  server_java_opts=`grep '^[ \t]*server\.java\.opts' ./conf/server.conf | sed 's/[ \t]*server\.java\.opts=//'`
  if [ -z "${server_java_opts}" ]; then
  echo "-XX:MaxPermSize=192m -Xmx512m -Xms512m"
  fi
  echo "${server_java_opts}"
}

doStart () {

# Setup JAVA_OPTS from server.conf
JAVA_OPTS=`loadJavaOpts`

# Start the server
echo "Booting the server (Using JAVA_OPTS=${JAVA_OPTS})..."

java ${JAVA_OPTS} -cp ${CLASSPATH} com.jsonljd.konga.lang.StartService -c ./conf/konga.xml -r ../assets

}

case "$1" in
  start)
    echo "Starting server..."
    doStart
    echo "server booted."
    ;;
  *)
    # Print help, don't advertise halt, it's nasty
    echo "Usage: $0 {start|stop}" 1>&2
    exit 1
    ;;
esac

exit 0
