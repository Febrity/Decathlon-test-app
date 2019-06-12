import React from 'react'
import axios from 'axios'
import Button from 'react-bootstrap/Button'
import ListGroup from 'react-bootstrap/ListGroup'

export default class WikiTitle extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            show: false,
            listTitles: [1, 2]
        }
    }

    click = () => {

        navigator.geolocation.watchPosition(
            (position) => {
                const latitude = position.coords.latitude;
                const longitude = position.coords.longitude;

                axios.get("/articles", {
                    params: {
                        lat: latitude,
                        lon: longitude
                    }
                }).then(res => {
                    const listTitles = res.data;
                    this.setState({
                        listTitles: listTitles,
                        show: true
                    });
                });
            },
            (error) => {
                axios.get("/articles", {
                    params: {
                        lat: null,
                        lon: null
                    }
                }).then(res => {
                    const listTitles = res.data;
                    this.setState({
                        listTitles: listTitles,
                        show: true
                    });
                });
            },
            {enableHighAccuracy: true, timeout: 20000, maximumAge: 1000, distanceFilter: 10},
        );
    }

    render() {

        const {show, listTitles} = this.state;

        let list;

        if (show) {
            list =
                <ListGroup>
                    {listTitles.map(e => <ListGroup.Item>{e}</ListGroup.Item>)}
                </ListGroup>


        }

        return (
            <div>
                <Button variant="info" onClick={this.click}>Show nearby articles from wikipedia</Button>
                {list}
            </div>
        );
    }

}