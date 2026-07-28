# CloudWise – Intelligent Cloud Cost Optimizer

## **Project Overview**
CloudWise is a cloud management platform that continuously monitors AWS resources, analyzes cloud spending, identifies unnecessary costs, and provides intelligent recommendations to reduce monthly cloud bills without affecting application performance.

## **Tech Stack**
- **Frontend**: React
- **Backend**: Java Spring Boot
- **Database**: MySQL
- **Cloud**: AWS (Cost Explorer API, CloudWatch, EC2, S3, RDS, IAM)

## **Features**
- User authentication and role-based access.
- Secure AWS account connection using IAM roles.
- Resource discovery for EC2, S3, RDS, EBS, and more.
- Cost analysis and waste detection.
- Intelligent recommendations for cost optimization.
- Dashboard for visualizing cloud costs and savings.

## **Setup Instructions**

### **Backend (Spring Boot)**
1. Navigate to the `backend` directory:
   ```bash
   cd backend
   ```
2. Build and run the Spring Boot application:
   ```bash
   ./mvnw spring-boot:run
   ```
   Or, if you have Maven installed:
   ```bash
   mvn spring-boot:run
   ```
3. The backend will start on `http://localhost:8080`.

### **Frontend (React)**
1. Navigate to the `frontend` directory:
   ```bash
   cd frontend
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the React development server:
   ```bash
   npm start
   ```
4. The frontend will start on `http://localhost:3000`.

### **Database (MySQL)**
1. Create a MySQL database named `cloudwise`.
2. Import the schema from `backend/src/main/resources/schema.sql`.
3. Update the database configuration in `backend/src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/cloudwise
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

## **AWS Configuration**
1. Create an IAM role with read-only access to AWS services (EC2, S3, RDS, etc.).
2. Update the AWS credentials in `backend/src/main/resources/application.properties`:
   ```properties
   aws.accessKeyId=your_access_key
   aws.secretKey=your_secret_key
   aws.region=your_region
   ```

## **API Endpoints**
| Endpoint | Method | Description |
|----------|--------|-------------|
| `/api/users/login` | POST | User login |
| `/api/aws/connect` | POST | Connect AWS account |
| `/api/aws/resources` | GET | Fetch AWS resources |
| `/api/costs` | GET | Get current cloud costs |
| `/api/recommendations` | GET | Get optimization recommendations |

## **Project Structure**
```
CloudWise-Intelligent-Cloud-Cost-Optimizer/
├── backend/
│   ├── pom.xml
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/cloudwise/
│   │   │   │   ├── config/
│   │   │   │   ├── controllers/
│   │   │   │   ├── models/
│   │   │   │   ├── services/
│   │   │   │   └── repositories/
│   │   │   └── resources/
│   │   │       ├── application.properties
│   │   │       └── schema.sql
│   │   └── test/
│   └── mvnw
├── frontend/
│   ├── public/
│   │   └── index.html
│   ├── src/
│   │   ├── components/
│   │   │   ├── Dashboard.js
│   │   │   ├── Login.js
│   │   │   └── ...
│   │   ├── App.js
│   │   ├── index.js
│   │   └── styles.css
│   ├── package.json
│   └── README.md
└── .gitignore
```

## **Contributing**
1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Commit your changes and push to your branch.
4. Open a pull request.

## **License**
This project is licensed under the MIT License.
