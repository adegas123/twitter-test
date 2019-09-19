import React, { Component } from 'react';
import './PostsByTime.css';
import { getPostsByTime } from '../util/APIUtils';
import { LineChart, Line, CartesianGrid, XAxis, YAxis, Tooltip, Legend, ResponsiveContainer } from 'recharts';

class PostsByTime extends Component {
    
    constructor(props) {
        super(props);
        console.log(props);
        this.state = {
            dataChart: [],
            opacity: {
                uv: 1
              }
        }
    }

    componentDidMount() {
        this.getPostsByTimeList();
    }
    getPostsByTimeList = async() => {
        try {
            getPostsByTime().then((value) => {
                var array = ['hour', 'count'];
                var newList = [];

                for(var item in value) {
                    var newData = {}

                    for (var a in array) {
                        if(a === '0') {
                         newData[array[a]] = value[item][Object.keys(value[item])[a]] + "h";
                        } else {
                          newData[array[a]] = value[item][Object.keys(value[item])[a]];
                        }
                    }
                    newList.push(newData);
                }
                console.log(newList);
                this.setState({dataChart: newList});
            })
        } catch (err) {
          console.log(err);
        }
    }
    handleMouseEnter = (o) => {
        const { dataKey } = o;
        const { opacity } = this.state;
    
        this.setState({
          opacity: { ...opacity, [dataKey]: 0.5 },
        });
      }
    handleMouseLeave = (o) => {
        const { dataKey } = o;
        const { opacity } = this.state;
    
        this.setState({
          opacity: { ...opacity, [dataKey]: 1 },
        });
      }
    render() {
        const { opacity } = this.state;
        return (
            <div className="topuser-container">
                <div className="container">
                    <div className="topuser-info">
                        <div className="topuser-name">
                            <h2>Posts along the day</h2>
                            <div style={{ width: '100%', height: 300 }}>
                                <ResponsiveContainer>
                                    <LineChart data={this.state.dataChart} margin={{ top: 5, right: 30, left: 20, bottom: 5 }}>
                                        <CartesianGrid strokeDasharray="3 3" />
                                        <XAxis dataKey="hour" />
                                        <YAxis />
                                        <Tooltip />
                                        <Legend onMouseEnter={this.handleMouseEnter} onMouseLeave={this.handleMouseLeave} />
                                        <Line type="monotone" dataKey="count" strokeOpacity={opacity.uv} stroke="#82ca9d" activeDot={{ r: 8 }} />
                                    </LineChart>
                                </ResponsiveContainer>
                            </div>
                        </div>
                    </div>
                </div>    
            </div>
        );
    }
}

export default PostsByTime