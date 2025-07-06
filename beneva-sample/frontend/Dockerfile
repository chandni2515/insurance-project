FROM node:14
WORKDIR /app
COPY package.json package.json
RUN npm install --silent
COPY . .
RUN npm run build
CMD ["npm", "start"]
