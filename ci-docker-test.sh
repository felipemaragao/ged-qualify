docker run -it   \
           --rm  \
           -v $PWD:$PWD \
           -w $PWD  \
           -v /var/run/docker.sock:/var/run/docker.sock arm64v8/maven:3.8-openjdk-8-slim \
           mvn test
