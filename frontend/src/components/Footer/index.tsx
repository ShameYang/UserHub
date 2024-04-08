import { GithubOutlined } from '@ant-design/icons';
import { DefaultFooter } from '@ant-design/pro-components';
import React from 'react';

const Footer: React.FC = () => {
  return (
    <DefaultFooter
      style={{
        background: 'none',
      }}
      links={[
        {
          key: 'project',
          title: 'UserHub',
          href: 'https://github.com/shameyang/userhub',
          blankTarget: true,
        },
        {
          key: 'github',
          title: <GithubOutlined />,
          href: 'https://github.com/shameyang',
          blankTarget: true,
        }
      ]}
    />
  );
};

export default Footer;
