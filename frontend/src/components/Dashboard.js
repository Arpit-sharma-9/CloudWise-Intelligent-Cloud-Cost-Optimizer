import React, { useState, useEffect } from 'react';
import axios from 'axios';

function Dashboard() {
  const [resources, setResources] = useState([]);
  const [totalCost, setTotalCost] = useState(0);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchResources = async () => {
      try {
        const response = await axios.get('/api/aws/resources?userId=1');
        setResources(response.data);
        const costResponse = await axios.get('/api/aws/costs?userId=1');
        setTotalCost(costResponse.data);
        setLoading(false);
      } catch (err) {
        console.error('Error fetching resources:', err);
        setLoading(false);
      }
    };
    fetchResources();
  }, []);

  if (loading) {
    return <div>Loading...</div>;
  }

  return (
    <div className="dashboard-container">
      <div className="dashboard-header">
        <h2>CloudWise Dashboard</h2>
        <div className="cost-summary">
          <h3>Total Cloud Cost: {totalCost}</h3>
        </div>
      </div>
      <div className="resource-list">
        <h3>AWS Resources</h3>
        <table>
          <thead>
            <tr>
              <th>Resource Type</th>
              <th>Resource Name</th>
              <th>Region</th>
              <th>Cost</th>
              <th>CPU Utilization</th>
            </tr>
          </thead>
          <tbody>
            {resources.map((resource) => (
              <tr key={resource.id}>
                <td>{resource.resourceType}</td>
                <td>{resource.resourceName}</td>
                <td>{resource.region}</td>
                <td>${resource.cost}</td>
                <td>{resource.cpuUtilization}%</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default Dashboard;
