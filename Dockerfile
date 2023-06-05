FROM adoptopenjdk:8-jdk-hotspot

# Install Scala
ENV SCALA_VERSION 2.12.12
RUN curl -LO "https://downloads.lightbend.com/scala/$SCALA_VERSION/scala-$SCALA_VERSION.tgz" && \
    tar -xf "scala-$SCALA_VERSION.tgz" && \
    mv "scala-$SCALA_VERSION" /usr/local/scala && \
    rm "scala-$SCALA_VERSION.tgz"

# Install SBT
ENV SBT_VERSION 1.8.2
RUN curl -LO "https://github.com/sbt/sbt/releases/download/v$SBT_VERSION/sbt-$SBT_VERSION.tgz" && \
    tar -xf "sbt-$SBT_VERSION.tgz" && \
    mv sbt /usr/local/sbt && \
    rm "sbt-$SBT_VERSION.tgz"

ENV PATH="/usr/local/scala/bin:/usr/local/sbt/bin:${PATH}"

# Set working directory
WORKDIR /app

# Copy and build your Scala project
COPY . /app
RUN sbt compile

# Default command
CMD sbt run
