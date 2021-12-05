import React, { Component } from 'react'

class DonationCatalog extends Component 
{
    constructor(props)
    {
        super(props)

        this.state = {
            donations: []
        }
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Donations</h2>
                <div className="row">
                    <table className="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Team Trees</th>
                                <th>Team Seas</th>
                                <th>Team Bees</th>
                                <th>Donate!</th>
                            </tr>
                        </thead>

                        <tbody>
                            {
                                this.state.donations.map(
                                    donation => 
                                    <tr key = {donation.id}>
                                        <td> { donation.name } </td>
                                        <td> { donation.tiers } </td>
                                        <td> { donation.notes } </td>
                                    </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>

            </div>
        )
    }
}

export default DonationCatalog