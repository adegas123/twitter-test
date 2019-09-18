import React, { Component } from 'react';
import './TopUsers.css';
import { getTopUsers } from '../util/APIUtils';

class TopUsers extends Component {
    constructor(props) {
        super(props);
        console.log(props);
        this.state = {
            topusers: []
        }
    }

    componentDidMount() {
        this.getTopUsersList();
    }
    getTopUsersList = async() => {
        try {
            getTopUsers().then(topusers => console.log(topusers));
            getTopUsers().then((value) => this.setState({topusers: value}));
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
                            <h2>Top 5 users by followers</h2>
                            <div className="pure-g">
                                {this.state.topusers.map((topuser, i) => (
                                    <div key={i} className="pure-u-1-5">{i+1}º<hr />{topuser.userName}<br /><hr />Followers: {topuser.followers}</div>
                                ))}
                            </div>
                        </div>
                    </div>
                </div>    
            </div>
        );
    }
}

export default TopUsers