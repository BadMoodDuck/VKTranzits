import React from "react";
import CalendarService from "../services/CalendarService";

class CalendarComponent extends React.Component{

    constructor() {
        super(props)
        this.state = {
            dates:[]
        }
    }

    componentDidMount() {
        CalendarService.getDates().then((response) => {
            this.setState({dates: response.dates})
        });
    }
}

export default CalendarComponent