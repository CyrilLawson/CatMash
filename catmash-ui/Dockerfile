FROM nginx
MAINTAINER Cyril LAWSON
WORKDIR /home
COPY /catmash-ui /home/catmash-ui
RUN rm /etc/nginx/conf.d/default.conf
COPY catmash-ui-nginx.conf /etc/nginx/conf.d
RUN apt-get update \
	&& apt-get install -y nodejs npm
RUN cd /home/catmash-ui/ \
    && yarn update \
	&& yarn install -g @angular/cli@11 \
	&& ng build --prod --base-href /catmash-ui/
RUN cp -r /home/catmash-ui/dist/catmash-ui /usr/share/nginx/html/catmash-ui
