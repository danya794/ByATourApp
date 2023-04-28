FROM node:latest
WORKDIR /opt/app
COPY . .
RUN npm install
CMD ["npm", "start"]
EXPOSE 9999