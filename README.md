# cyse7220-toyswap-backend

## Team Information

| Name | NEU ID | Email Address |
| --- | --- | --- |
| Pratik Patil | 001499015 | patil.prat@husky.neu.edu|
| Lakshit Talreja|001475200 |talreja.l@husky.neu.edu |

## To run locally for development
First, load the project in Eclipse or any IDE
```
then import dependencies form pom.xml, by runnning, mvn clean install

Finally, Run ToyswapApplication.java as java application

## To build docker container
Run the following docker command
```html
sudo docker build -t <docker-username>/csye7220-toyswap-backend:latest
```

## To run from docker container
Run the following docker command
```html
docker run -p 1002:80 -e API_URL=<url-domain> -e API_PORT=<exposed-api-port> -d <docker-username>/csye7220-toyswap-backend:latest
```
