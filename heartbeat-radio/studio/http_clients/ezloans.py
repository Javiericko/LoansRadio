import requests
# Could use tenacity for retrying requests
from studio.models import Loan

# In this part it'd be interesting to start using a config
# object with the project's settings. Such objects play really
# well with .env files and Consul.
class EzLoansClient:
    # Could start defining autorization attributes to use with requests
    base_url: str = "http://0.0.0.0:9000"

    def get_ridiculous_loan(self):
        response = requests.get(f"{self.base_url}/api/loans/momoneymoproblems")

        return Loan(**response.json())

EzLoans = EzLoansClient()
