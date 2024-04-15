# Primera etapa: construir la aplicación
FROM node:16.13.2 AS builder

WORKDIR /app

COPY package*.json ./
RUN npm install -g npm@8.1.2
RUN npm install -g @angular/cli@15.2.4
RUN npm ci
COPY . .

RUN npm run build --prod

# Segunda etapa: construir la imagen de producción
FROM nginx:1.23.3

COPY --from=builder /app/dist/bank-frontend /usr/share/nginx/html

COPY nginx-custom.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
