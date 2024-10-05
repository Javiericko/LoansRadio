import dash_bootstrap_components as dbc
import dash_html_components as html
from dash.dependencies import Input, Output
from django_plotly_dash import DjangoDash

from studio.http_clients.ezloans import EzLoans

app = DjangoDash('EzLoans', external_stylesheets=[dbc.themes.BOOTSTRAP])


card_main = dbc.Card(id='loan', color="success", inverse=True)

app.layout = html.Div([
    html.H1(children='Ez Loans'),
    dbc.Row([dbc.Col(card_main, width=3)], justify="center"),
])


@app.callback(
    Output("loan", "children"),
    [Input('loan', 'children')]
)
def show_loan(value):
    # Get a loan from the Ez Loans API, just hardcoded one for now
    loan = EzLoans.get_ridiculous_loan()

    card = dbc.CardBody([
                html.H4(f"{loan.idNumber}", className="card-title"),
                html.P(f"Amount: {loan.amount}", className="card-text"),
                html.P(f"Interest Rate: {loan.interestRate}", className="card-text"),
                html.P(f"Length: {loan.length}", className="card-text"),
                html.P(f"Payment Amount: {loan.paymentAmount}", className="card-text"),
            ]
        )

    return card