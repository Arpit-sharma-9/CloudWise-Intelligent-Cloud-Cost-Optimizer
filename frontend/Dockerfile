# Dockerfile for CloudWise Frontend (React)
# Use a multi-stage build to reduce final image size

# Stage 1: Build the React app
FROM node:18-alpine AS build
WORKDIR /app

# Copy package.json and package-lock.json
COPY package.json ./
COPY package-lock.json ./

# Install dependencies
RUN npm install

# Copy the source code
COPY src ./src
COPY public ./public

# Build the React app
RUN npm run build

# Stage 2: Serve the app with Nginx
FROM nginx:alpine
WORKDIR /usr/share/nginx/html

# Copy the built app from the build stage
COPY --from=build /app/build ./

# Copy the custom Nginx configuration
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Expose port 3000 (or 80 for Nginx)
EXPOSE 3000

# Start Nginx
CMD ["nginx", "-g", "daemon off;"]
