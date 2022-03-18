import React from 'react';

const BorderButton = ({ title }) => {
  return (
    <button className="p-2 w-full border-2 border-white rounded-full text-white text-lg hover:bg-white/20">
      {title}
    </button>
  );
};

export default BorderButton;
