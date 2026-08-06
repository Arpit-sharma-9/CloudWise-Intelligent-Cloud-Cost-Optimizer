# CloudWise – Intelligent Cloud Cost Optimizer 

## **Project Overview**
CloudWise is a cloud management platform that continuously monitors AWS resources, analyzes cloud spending, identifies unnecessary costs, and provides intelligent recommendations to reduce monthly cloud bills without affecting application performance.

## **Tech Stack**
- **Frontend**: React
- **Backend**: Java Spring Boot
- **Database**: SQLite (Zero-config, file-based)
- **Cloud**: AWS (Cost Explorer API, CloudWatch, EC2, S3, RDS, IAM)

## **Features**
- User authentication and role-based access.
- Secure AWS account connection using IAM roles.
- Resource discovery for EC2, S3, RDS, EBS, and more.
- Cost analysis and waste detection.
- Intelligent recommendations for cost optimization.
- Dashboard for visualizing cloud costs and savings.

---

## **Quick Start (Docker)**
The easiest way to run CloudWise is using Docker. This method requires **no manual setup** for databases, Java, or Node.js.

### **Prerequisites**
- [Docker](https://www.docker.com/get-started) (Installed and running)
- [Docker Compose](https://docs.docker.com/compose/install/) (Included with Docker Desktop)

### **Steps**
1. **Clone the repository:**
   ```bash
   git clone https://github.com/Arpit-sharma-9/CloudWise-Intelligent-Cloud-Cost-Optimizer.git
   cd CloudWise-Intelligent-Cloud-Cost-Optimizer
   ```

2. **Configure AWS and JWT (Optional):**
   - Copy the `.env.example` file to `.env`:
     ```bash
     cp .env.example .env
     ```
   - Edit `.env` and add your AWS credentials and JWT secret:
     ```env
     AWS_ACCESS_KEY_ID=your_aws_access_key
     AWS_SECRET_KEY=your_aws_secret_key
     AWS_REGION=us-east-1
     JWT_SECRET=your_jwt_secret_key
     ```

3. **Run the project:**
   ```bash
   docker-compose up --build
   ```
   - This will:
     - Build the backend (Spring Boot) and frontend (React).
     - Start the backend on `http://localhost:8080`.
     - Start the frontend on `http://localhost:3000`.
     - Create a SQLite database file (`backend/data/cloudwise.db`).

4. **Access the app:**
   - Open your browser and go to: **[http://localhost:3000](http://localhost:3000)**

---

## **Manual Setup (Without Docker)**
If you prefer to run the project without Docker, follow these steps:

### **Backend (Spring Boot)**
1. **Install Java 17+ and Maven:**
   - Download [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).
   - Install [Maven](https://maven.apache.org/install.html).

2. **Navigate to the `backend` directory:**
   ```bash
   cd backend
   ```

3. **Build and run the backend:**
   ```bash
   mvn spring-boot:run
   ```
   - The backend will start on `http://localhost:8080`.
   - A SQLite database file (`cloudwise.db`) will be created automatically.

### **Frontend (React)**
1. **Install Node.js 18+ and npm:**
   - Download [Node.js](https://nodejs.org/).

2. **Navigate to the `frontend` directory:**
   ```bash
   cd frontend
   ```

3. **Install dependencies and start the app:**
   ```bash
   npm install
   npm start
   ```
   - The frontend will start on `http://localhost:3000`.

---

## **Environment Variables**
| Variable | Description | Default |
|----------|-------------|---------|
| `AWS_ACCESS_KEY_ID` | AWS IAM Access Key | `your_aws_access_key` |
| `AWS_SECRET_KEY` | AWS IAM Secret Key | `your_aws_secret_key` |
| `AWS_REGION` | AWS Region | `us-east-1` |
| `JWT_SECRET` | JWT Secret for Authentication | `your_jwt_secret_key` |

---

## **API Endpoints**
| Endpoint | Method | Description |
|----------|--------|-------------|
| `/api/users/login` | POST | User login |
| `/api/aws/connect` | POST | Connect AWS account |
| `/api/aws/resources` | GET | Fetch AWS resources |
| `/api/costs` | GET | Get current cloud costs |
| `/api/recommendations` | GET | Get optimization recommendations |

---

## **Project Structure**
```
CloudWise-Intelligent-Cloud-Cost-Optimizer/
├── backend/
│   ├── Dockerfile
│   ├── pom.xml
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/cloudwise/
│   │   │   │   ├── config/
│   │   │   │   ├── controllers/
│   │   │   │   ├── models/
│   │   │   │   ├── repositories/
│   │   │   │   └── services/
│   │   │   └── resources/
│   │   │       ├── application.properties
│   │   │       └── schema.sql
│   │   └── test/
│   └── mvnw
├── frontend/
│   ├── Dockerfile
│   ├── nginx.conf
│   ├── package.json
│   ├── public/
│   │   └── index.html
│   └── src/
│       ├── components/
│       │   ├── Dashboard.js
│       │   ├── Login.js
│       │   └── ...
│       ├── App.js
│       ├── index.js
│       └── styles.css
├── docker-compose.yml
├── .env.example
└── README.md
```

---

## **Troubleshooting**
### **Docker Issues**
- **Error: Port already in use** → Stop the conflicting service or change the port in `docker-compose.yml`.
- **Error: Docker not running** → Start Docker Desktop or the Docker daemon.
- **Error: Build fails** → Ensure you have enough disk space and internet connectivity.

### **Manual Setup Issues**
- **Backend fails to start** → Ensure Java 17+ and Maven are installed.
- **Frontend fails to start** → Ensure Node.js 18+ is installed.
- **Database issues** → Delete the `cloudwise.db` file and restart the backend.

---

## **Contributing**
1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Commit your changes and push to your branch.
4. Open a pull request.

---

## **License**
This project is licensed under the MIT License.
