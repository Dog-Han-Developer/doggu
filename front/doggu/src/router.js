import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import DogAuth from './pages/dog-auth';
import Login from './pages/login';
import Signup from './pages/signup';

function router() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/dog-auth" element={<DogAuth />} />
      </Routes>
    </Router>
  );
}

export default router;
