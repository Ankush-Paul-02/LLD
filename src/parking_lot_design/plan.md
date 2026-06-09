# Requirements Gathering

1. car came
2. ticket generated
3. space allotment
4. do payment during exit
5. free up space


How many entrances?
- 1 entrance and 1 exit
- floors - no


Objets:
1. Vehicle - (id, type)
2. Ticket - (id, entry, spot, price by type)
3. Entrance - find parking space and update parking space and generate ticket
4. Exit - cost calculation
5. Parking space - for now two-wheeler and four wheeler - extensible in future - (id, price, isAvailable, occupied by, type)
6. Payment - can be hourly or minutes (strategy design)

follow up:
1. nearest parking space