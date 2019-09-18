import React, { Component } from 'react';
import './PostsByTime.css';
import { getPostsByTime } from '../util/APIUtils';
import { LineChart, Line, CartesianGrid, XAxis, YAxis } from 'recharts';
const data = [
    {name: 'Page A', uv: 4000, pv: 2400, amt: 2400},
    {name: 'Page B', uv: 3000, pv: 1398, amt: 2210},
    {name: 'Page C', uv: 2000, pv: 9800, amt: 2290},
    {name: 'Page D', uv: 2780, pv: 3908, amt: 2000},
    {name: 'Page E', uv: 1890, pv: 4800, amt: 2181},
    {name: 'Page F', uv: 2390, pv: 3800, amt: 2500},
    {name: 'Page G', uv: 3490, pv: 4300, amt: 2100},
];

const renderLineChart = (
  <LineChart width={600} height={300} data={data}>
    <Line type="monotone" dataKey="uv" stroke="#8884d8" />
    <CartesianGrid stroke="#ccc" />
    <XAxis dataKey="name" />
    <YAxis />
  </LineChart>
);

class PostsByTime extends Component {
    
    constructor(props) {
        super(props);
        console.log(props);
        this.state = {
            posts: [],
            dataChart: []
        }
    }

    componentDidMount() {
        this.getPostsByTimeList();
    }
    getPostsByTimeList = async() => {
        try {
            getPostsByTime().then((value) => {
                let items = []
                value.foreach((post) => {
                    let item = {
                        hour: post.hour,
                        count: post.count
                    }
                    items.push(item);
                });
                this.setState({dataChart: items});
                console.log(this.state.dataChart)
            })
        } catch (err) {
          console.log(err);
        }
    }
    render() {
        return (
            <div className="topuser-container">
                <div className="container">
                    <div className="topuser-info">
                        <div className="topuser-name">
                            <h2>Posts by Time</h2>
                            {renderLineChart}
                           <ul className="list-group">{this.state.posts.map((post, i) => (
                               <li key={i} className="list-group-item">
                                    {post.hour} - qty: {post.count}
                                </li>
                                ))}
                            </ul>
                        </div>
                    </div>
                </div>    
            </div>
        );
    }
}

export default PostsByTime