# Online Auction System

## Overview
The Online Auction System allows sellers to host auctions where registered buyers can bid on items. The system supports multiple simultaneous auctions and provides a clear mechanism to determine winners, calculate seller profits, and manage buyer statuses.

## Functionalities

### Core Functionalities
1. **Add Buyer:** Add a buyer to the system.
2. **Add Seller:** Add a seller to the system.
3. **Create Auction:** Initialize a new auction with:
   - Auction ID
   - Lowest and highest bid limits
   - Participation cost
   - Associated seller
4. **Create/Update Bid:**
   - Allow buyers to bid on an auction.
   - Update an existing bid for an auction.
5. **Withdraw Bid:**
   - Allow buyers to withdraw their bid before the auction closes.
6. **Close Auction:**
   - Determine the winning bid using the highest unique bid.
   - Handle cases with no winners.
7. **Get Profit/Loss:**
   - Calculate profit or loss for a seller based on:
     - Winning auction price
     - Participation cost share
     - Average of the lowest and highest bid limits

### Bonus Functionality
1. **Preferred Buyer Status:**
   - Upgrade a buyer to a preferred status after participating in more than 2 auctions.
   - Use preferred buyer status to resolve ties in highest unique bids.
2. **Validation:**
   - Enforce bid limits to ensure bids are within the defined range.

## Example Test Cases

### Test Case 1
#### Actions:
1. Add buyers and seller:
   - `ADD_BUYER("buyer1")`
   - `ADD_BUYER("buyer2")`
   - `ADD_BUYER("buyer3")`
   - `ADD_SELLER("seller1")`
2. Create auction:
   - `CREATE_AUCTION("A1", "10", "50", "1", "seller1")`
3. Buyers place bids:
   - `CREATE_BID("buyer1", "A1", "17")`
   - `CREATE_BID("buyer2", "A1", "15")`
   - `UPDATE_BID("buyer2", "A1", "19")`
   - `CREATE_BID("buyer3", "A1", "19")`
4. Close auction:
   - `CLOSE_AUCTION("A1")` // Winner: Buyer1
5. Get seller profit:
   - `GET_PROFIT("seller1", "A1")` // Profit: -12.4

### Test Case 2
#### Actions:
1. Add another seller:
   - `ADD_SELLER("seller2")`
2. Create auction:
   - `CREATE_AUCTION("A2", "5", "20", "2", "seller2")`
3. Invalid bid:
   - `CREATE_BID("buyer3", "A2", "25")` // Fails (exceeds highest bid limit)
4. Valid bid:
   - `CREATE_BID("buyer2", "A2", "5")`
5. Withdraw bid:
   - `WITHDRAW_BID("buyer2", "A2")`
6. Close auction:
   - `CLOSE_AUCTION("A2")` // No winner
7. Get seller profit:
   - `GET_PROFIT("seller2", "A2")` // Profit: 0.4

## Object-Oriented Design

### Key Classes
1. **User:**
   - Base class for buyers and sellers.
   - Attributes: `name`, `type` (buyer/seller).
2. **Buyer (extends User):**
   - Attributes: `participated_auctions` (to track preferred status).
3. **Seller (extends User):**
   - Attributes: `auctions` (list of auctions hosted).
4. **Auction:**
   - Attributes: `id`, `lowest_bid_limit`, `highest_bid_limit`, `participation_cost`, `seller`, `bids`, `status` (open/closed).
5. **Bid:**
   - Attributes: `buyer`, `amount`.
6. **AuctionSystem:**
   - Central system to manage users, auctions, bids, and interactions.

### Contracts/Interfaces
- **User Interface:** Methods for adding users.
- **Auction Interface:** Methods for creating auctions, managing bids, and closing auctions.
- **Profit Calculator:** Separate service to compute seller profit/loss.
- **Validation Service:** To enforce business rules (e.g., bid limits).

## Edge Cases
1. No bids in an auction.
2. All bids are tied or non-unique.
3. Buyer tries to bid below the lowest bid limit or above the highest bid limit.
4. Buyer withdraws the only valid unique bid.
5. Multiple preferred buyers tied for the highest bid.

## Extensibility
- Use interfaces for auction creation, bid management, and profit calculation to allow easy addition of new features.
- Modular services for validation and preferred buyer handling.
- Extend buyer and seller classes for additional attributes or roles.

## Error Handling
- Invalid bid amounts should raise descriptive errors.
- Closing an auction with no bids should return no winner gracefully.
- Ensure system stability when handling simultaneous auctions.

## Demo Instructions
1. Run the main program or test cases.
2. Use provided commands (e.g., `ADD_BUYER`, `CREATE_AUCTION`) to simulate scenarios.
3. Verify outputs against expected results in the test cases.

## Next Steps
1. Implement classes and methods based on the design.
2. Write test cases for all edge cases and scenarios.
3. Create a CLI or web interface for interaction.
4. Document the system for developers and users.

