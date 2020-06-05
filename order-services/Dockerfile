FROM java
MAINTAINER vawu 2134200188@qq.com
WORKDIR /
COPY order-provider/target/*.jar app.jar
ENV JAVA_OPTS="\
-server \
-Xmx215m \
-Xmx512m \
-Xmn215m \
-XX:SurvivorRatio=1 \
-XX:MetaspaceSize=256m \
-XX:MaxMetaspaceSize=256m \
-XX:ParallelGCThreads=4 \
-XX:+PrintGCDetails \
-XX:+PrintTenuringDistribution \
-XX:+PrintGCTimeStamps \
-XX:+HeapDumpOnOutOfMemoryError \
-XX:HeapDumpPath=/ \
-Xloggc:/logs/gc.log \
-XX:+UseGCLogFileRotation \
-XX:NumberOfGCLogFiles=5 \
-XX:+DisableExplicitGC \
-XX:+UseConcMarkSweepGC \
-XX:+UseParNewGC \
-XX:+CMSParallelRemarkEnabled \
-XX:+CMSClassUnloadingEnabled \
-XX:LargePageSizeInBytes=128M \
-XX:+UseFastAccessorMethods \
-XX:+UseCMSInitiatingOccupancyOnly \
-XX:CMSInitiatingOccupancyFraction=80 \
-XX:SoftRefLRUPolicyMSPerMB=0 \
-XX:+PrintClassHistogram \
-XX:+PrintHeapAtGC \
-XX:+UnlockDiagnosticVMOptions \
-XX:+UnlockExperimentalVMOptions \
-XX:+PrintFlagsFinal \
-XX:GCLogFileSize=10M"
ENTRYPOINT java ${JAVA_OPTS} -jar app.jar